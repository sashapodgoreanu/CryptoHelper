/*
 * Copyright 2014 Luigi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cryptohelper.proxy;

import java.io.*;

/**
 *
 * @author Luigi
 */
public class RealFileHandler extends FileHandler {

    private byte[] content;

    public RealFileHandler(String fName) {
        super(fName);
        System.out.println("(creating a real handler with file content)");

        FileInputStream file;
        try {
            file = new FileInputStream(fName);
            int numBytes = file.available();
            content = new byte[numBytes];
            file.read(content);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getContent() {
        return new String(content);
    }

    public String getLine(int requestedLine) {
        System.out.println("(accessing from real handler)");
        int numBytes = content.length;
        int currentLine = 1;
        int startingPos = -1;
        int lineLength = 0;
        for (int i = 0; i < numBytes; i++) {
            if ((currentLine == requestedLine)
                    && (content[i] != 0x0A)) {
                if (startingPos == -1) {
                    startingPos = i;
                }
                lineLength++;
            }
            if (content[i] == 0x0D) {
                currentLine++;
            }
        }
        String lineText = "";
        if (startingPos != -1) {
            lineText = new String(content, startingPos, lineLength - 1);
        }
        return "\"" + lineText + "\"";
    }
}
