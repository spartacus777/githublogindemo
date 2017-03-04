# Task Description

The goal is to create application to list user’s repositories in github.
### Requirements

What have to be done:
 -  Use public github API ( ​https://developer.github.com/v3 ​). You are not allowed to use any
of github SDK libraries.
 -  Login with username/password.
 -  Display list of user repositories (using GET /user/repos). Display repository name, stars
count and last update date. In case of multi-paged response load and display only first
page.
 -  App should gracefully handle screen orientation changes, wake-ups from suspended
state, minimizing/restoring.
 -  In case of service error display error messages came from github server.
Implementation Notes

Do not waste time polishing UI. It should be simple and not time consuming. Spend time on
handling orientation changes. Treat executing requests  as very expensive and try to not
cancel-restart a request after screen rotation. Offline work is optional and not required. Code
should be written in a manner to allow easy test coverage later. Pick a reliable architecture for
this task that will aid the project to evolve to fully-functional github mobile client with a lot of
functionality.
