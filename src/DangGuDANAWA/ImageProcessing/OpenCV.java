/*
OpenCV for Android NDK
Copyright (c) 2006-2009 SIProp Project http://www.siprop.org/

This software is provided 'as-is', without any express or implied warranty.
In no event will the authors be held liable for any damages arising from the use of this software.
Permission is granted to anyone to use this software for any purpose,
including commercial applications, and to alter it and redistribute it freely,
subject to the following restrictions:

1. The origin of this software must not be misrepresented; you must not claim that you wrote the original software. If you use this software in a product, an acknowledgment in the product documentation would be appreciated but is not required.
2. Altered source versions must be plainly marked as such, and must not be misrepresented as being the original software.
3. This notice may not be removed or altered from any source distribution.
 */
package DangGuDANAWA.ImageProcessing;


public class OpenCV {
    static {
        System.loadLibrary("opencv");
    }
    
    public native float[]   setbyteSourceImage(byte[] data, int w, int h);
    public native byte[]    findContours(int[] data, int w, int h);
    public native byte[]    getSourceImage(float[] data, boolean change);	// C단에서 자바단으로 이미지 파일을 보냄
    public native boolean	setSourceImage(int[] data, int w, int h);	// 폰에서 얻은 이미지 파일을 C단으로 보냄

}

