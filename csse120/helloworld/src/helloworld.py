from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app


class MainPage(webapp.RequestHandler):
    
    
    def get(self):
        self.response.headers['Content-Type'] = 'text/plain'
        self.response.out.write('Hello, webapp World!')
        self.response.out.write("<br>YOu are Blessed")
        #self.requests.get('http://econpy.pythonanywhere.com/ex/001.html')


app = webapp.WSGIApplication([('/', MainPage)], debug=True)


def main():
    run_wsgi_app(app)

if __name__ == "__main__":
    main()
