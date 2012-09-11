package thesmith.example

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class ExampleServlet extends HttpServlet {
  
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = resp.getWriter().print("pong")
  
}