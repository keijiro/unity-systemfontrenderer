#import <Foundation/Foundation.h>
#import <CoreGraphics/CoreGraphics.h>
#import <OpenGLES/EAGL.h>
#import <OpenGLES/ES1/gl.h>

extern EAGLContext* _context;

extern "C" void _SystemFontRendererRenderTextToTexture(const char *rawText, int width, int height, int textSize, int textureID) {
	EAGLContext *oldContext = [EAGLContext currentContext];
	if (oldContext != _context) [EAGLContext setCurrentContext:_context];

    void *textureData = malloc(width * height * 4);
    
    CGContextRef ctx = CGBitmapContextCreate(textureData, width, height, 8, width * 4, CGColorSpaceCreateDeviceRGB(), kCGImageAlphaPremultipliedLast | kCGBitmapByteOrder32Big);

    if (rawText) {
        CGContextSetTextDrawingMode(ctx, kCGTextFill);
        CGContextSetRGBFillColor(ctx, 1.0f, 1.0f, 1.0f, 1.0f);
        
        UIGraphicsPushContext(ctx);
        UIFont *font = [UIFont systemFontOfSize:textSize];
        
        CGFloat y = 0;
        for (NSString *line in [[NSString stringWithUTF8String:rawText] componentsSeparatedByString:@"\n"]) {
            [line drawAtPoint:CGPointMake(0, y) withFont:font];
            y += textSize;
        }
        
        UIGraphicsPopContext();
    }

    CGContextRelease(ctx);
    
    glBindTexture(GL_TEXTURE_2D, textureID);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, textureData);
    
    free(textureData);
}