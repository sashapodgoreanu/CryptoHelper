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

/**
 *
 * @author Luigi
 */
public class ProxyFileHandler extends FileHandler {

    private RealFileHandler realHandler;
    private int lineNumber;
    private String lineText;

    public ProxyFileHandler(String fName) {
        super(fName);
        System.out.println("(creating a proxy cache)");
    }

    public String getContent() {
        if (realHandler == null) {
            realHandler = new RealFileHandler(fileName);
        }
        return realHandler.getContent();
    }

    public String getLine(int requestedLine) {
        if (requestedLine == lineNumber) {
            System.out.println("(accessing from proxy cache)");
            return lineText;
        } else {
            if (realHandler == null) {
                realHandler = new RealFileHandler(fileName);
            }
            lineText = realHandler.getLine(requestedLine);
            lineNumber = requestedLine;
        }
        return lineText;
    }
}
