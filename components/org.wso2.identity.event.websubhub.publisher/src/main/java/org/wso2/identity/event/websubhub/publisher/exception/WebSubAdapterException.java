/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.identity.event.websubhub.publisher.exception;

/**
 * Exception wrapper class for WebSubHub Adapter exceptions.
 */
public class WebSubAdapterException extends Exception {

    private String errorCode;

    private String description;

    public WebSubAdapterException(String message, String errorCode) {

        super(message);
        this.errorCode = errorCode;
    }

    public WebSubAdapterException(String message, String description, String errorCode) {

        super(message);
        this.description = description;
        this.errorCode = errorCode;
    }

    public WebSubAdapterException(String message, String description, String errorCode, Throwable cause) {

        super(message, cause);
        this.description = description;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {

        return errorCode;
    }

    public String getDescription() {

        return description;
    }
}
