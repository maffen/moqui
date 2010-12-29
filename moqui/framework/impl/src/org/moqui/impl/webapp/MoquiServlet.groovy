/*
 * This Work is in the public domain and is provided on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
 * including, without limitation, any warranties or conditions of TITLE,
 * NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE.
 * You are solely responsible for determining the appropriateness of using
 * this Work and assume any risks associated with your use of this Work.
 *
 * This Work includes contributions authored by David E. Jones, not as a
 * "work for hire", who hereby disclaims any copyright to the same.
 */
package org.moqui.impl.webapp

import javax.servlet.http.HttpServlet
import org.slf4j.LoggerFactory
import org.slf4j.Logger
import javax.servlet.ServletException
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.ServletConfig
import org.moqui.Moqui
import org.moqui.context.WebExecutionContext

class MoquiServlet extends HttpServlet {
    protected final static Logger logger = LoggerFactory.getLogger(MoquiServlet.class)

    public MoquiServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger.info("Loading Moqui Webapp [" + config.getServletContext().getContextPath().substring(1) + "] " + config.getServletContext().getServletContextName() + ", located at " + config.getServletContext().getRealPath("/"))
        // TODO: anything to init here, or all per request that is not global?
    }

    /** @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /** @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: if resource is a static resource do not initialize wec, just stream through the resource (how to determine a static resource?)
        WebExecutionContext wec = Moqui.initWebExecutionContext(request, response)

        // TODO: render screens based on path in URL (should probably move this to another class that renders screens)
        String pathInfo = request.getPathInfo();
        List<String> pathElements = pathInfo.split("/") as List
        //wec.screen.renderScreenText(String screenLocation, Appendable appender, "html", String characterEncoding, null)

        // make sure everything is cleaned up
        Moqui.destroyActiveExecutionContext()
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}