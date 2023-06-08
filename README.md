The Task
--------

Implement a service for downloading web pages. It should provide HTTP API for adding download 
tasks. Results of the downloading should be stored in a file.

Server startup options:

    --max-parallel=N - maximum amount of requests sent in parallel overall
    --max-per-domain=N - maximum amount of requests sent in parallel for each domain
    --out=filename - path to store the output data

HTTP API specification:

**POST** `/download` - download an URL. POST body should contain the URL 
to download, in UTF8 encoding.  The server should respond with an id of the 
download task, returning the response immediately, before starting 
to download the page. Actual download result is saved to the output 
file (set at startup) on completion. The server should always accept tasks, 
unless the input data is invalid.

The service saves the result of a download to a file. 
The result should be a JSON-encoded object with the following fields:

* `id` - id of the download task, the same as returned to the user.
* `url` - requested URL
* `status` - either HTTP status code (a number converted to string, e.g. 
  "200"), or a string which describes an error which happened (e.g. 
  "connection error" - feel free to choose your own names for them).
* `responseSize` - size of the response body, in bytes. It should 
  include only response body size, excluding size of HTTP headers and 
  other information.

Example:
 
    {"id": 432, "url": 'https://example.com", "status": "200", "responseSize": 648}

The real incoming stream of requests will have an imbalance between domains,
i.e., there can be 500 requests to website A and 10 requests to website B.
It is important to keep the full usage of downloader resources and at the 
same time keep downloader polite to websites, by limiting the amount of 
URLs requested from a single website.
***
