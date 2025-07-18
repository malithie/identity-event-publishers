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

package org.wso2.identity.event.websubhub.publisher.config;

import org.wso2.identity.event.websubhub.publisher.constant.WebSubHubAdapterConstants;
import org.wso2.identity.event.websubhub.publisher.exception.WebSubAdapterException;
import org.wso2.identity.event.websubhub.publisher.util.WebSubHubAdapterUtil;

import java.util.Map;

/**
 * WebSubHub Adapter Configuration.
 */
public class WebSubAdapterConfiguration {

    private static final String ADAPTER_ENABLED_CONFIG = "enabled";
    private static final String ADAPTER_HUB_URL_CONFIG = "baseUrl";
    private static final String HTTP_CONNECTION_TIMEOUT = "httpConnectionTimeout";
    private static final String HTTP_READ_TIMEOUT = "httpReadTimeout";
    private static final String HTTP_CONNECTION_REQUEST_TIMEOUT = "httpConnectionRequestTimeout";
    private static final String DEFAULT_MAX_CONNECTIONS = "defaultMaxConnections";
    private static final String DEFAULT_MAX_CONNECTIONS_PER_ROUTE = "defaultMaxConnectionsPerRoute";
    private static final String MTLS_ENABLED = "mtlsEnabled";
    private final boolean adapterEnabled;
    private final int httpConnectionTimeout;
    private final int httpReadTimeout;
    private final int httpConnectionRequestTimeout;
    private final int defaultMaxConnections;
    private final int defaultMaxConnectionsPerRoute;
    private final boolean mtlsEnabled;
    private String webSubHubBaseUrl;


    /**
     * Initialize the {@link WebSubAdapterConfiguration}.
     *
     * @param properties Map of properties to initialize the configuration.
     * @throws WebSubAdapterException on failures when creating the configuration object.
     */
    public WebSubAdapterConfiguration(Map<String, String> properties) throws WebSubAdapterException {

        this.adapterEnabled = Boolean.parseBoolean(properties.get(ADAPTER_ENABLED_CONFIG));

        if (this.adapterEnabled) {
            this.webSubHubBaseUrl = properties.get(ADAPTER_HUB_URL_CONFIG);
            if (this.webSubHubBaseUrl == null) {
                throw WebSubHubAdapterUtil.handleClientException(
                        WebSubHubAdapterConstants.ErrorMessages.WEB_SUB_BASE_URL_NOT_CONFIGURED);
            }
        }

        this.mtlsEnabled = Boolean.parseBoolean(properties.get(MTLS_ENABLED));
        this.httpConnectionTimeout = parseIntOrDefault(
                properties.get(HTTP_CONNECTION_TIMEOUT),
                WebSubHubAdapterConstants.Http.DEFAULT_HTTP_CONNECTION_TIMEOUT);
        this.httpReadTimeout = parseIntOrDefault(
                properties.get(HTTP_READ_TIMEOUT),
                WebSubHubAdapterConstants.Http.DEFAULT_HTTP_READ_TIMEOUT);
        this.httpConnectionRequestTimeout = parseIntOrDefault(
                properties.get(HTTP_CONNECTION_REQUEST_TIMEOUT),
                WebSubHubAdapterConstants.Http.DEFAULT_HTTP_CONNECTION_REQUEST_TIMEOUT);
        this.defaultMaxConnections = parseIntOrDefault(
                properties.get(DEFAULT_MAX_CONNECTIONS),
                WebSubHubAdapterConstants.Http.DEFAULT_HTTP_MAX_CONNECTIONS);
        this.defaultMaxConnectionsPerRoute = parseIntOrDefault(
                properties.get(DEFAULT_MAX_CONNECTIONS_PER_ROUTE),
                WebSubHubAdapterConstants.Http.DEFAULT_HTTP_MAX_CONNECTIONS_PER_ROUTE);
    }

    private int parseIntOrDefault(String value, int defaultValue) {

        try {
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Getter method to return adapter enable configuration.
     *
     * @return whether adapter is enabled in the configurations.
     */
    public boolean isAdapterEnabled() {

        return adapterEnabled;
    }

    /**
     * Returns the base URL of the WebSubHub.
     *
     * @return base URL of the WebSubHub.
     */
    public String getWebSubHubBaseUrl() {

        return webSubHubBaseUrl;
    }

    /**
     * Returns the HTTP connection timeout.
     *
     * @return HTTP connection timeout.
     */
    public int getHTTPConnectionTimeout() {

        return httpConnectionTimeout;
    }

    /**
     * Returns the HTTP read timeout.
     *
     * @return HTTP Read Timeout.
     */
    public int getHttpReadTimeout() {

        return httpReadTimeout;
    }

    /**
     * Returns the http connection request timeout.
     *
     * @return http connection request timeout.
     */
    public int getHttpConnectionRequestTimeout() {

        return httpConnectionRequestTimeout;
    }

    /**
     * Returns the default max connections.
     *
     * @return default max connections.
     */
    public int getDefaultMaxConnections() {

        return defaultMaxConnections;
    }

    /**
     * Returns the default max connections per route.
     *
     * @return default max connections per route.
     */
    public int getDefaultMaxConnectionsPerRoute() {

        return defaultMaxConnectionsPerRoute;
    }

    /**
     * Returns whether mTLS is enabled.
     *
     * @return true if mTLS is enabled, false otherwise.
     */
    public boolean isMtlsEnabled() {

        return mtlsEnabled;
    }
}
