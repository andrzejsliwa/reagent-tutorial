# reagent-tutorial

## Steps 

   Install deps

    $ npm install
    
  Generate `pom.xml` for usage with IntelliJ [Setting up a Reagent Clojurescript project with Shadow-Cljs and Cursive](https://ghufran.posthaven.com/setting-up-a-reagent-clojurescript-project-with-shadow-cljs-and-cursive)
    
    $ npx shadow-cljs pom
    
  Run application
    
    $ npx shadow-cljs watch :app
  
  Wait until compilation is over and open [page](http://localhost:3000/)
   
  Connect to nREPL on port `55555`
  
  Select app in repl
  
    Connecting to remote nREPL server...
    Clojure 1.10.1
    
    (shadow.cljs.devtools.api/nrepl-select :app)
    To quit, type: :cljs/quit
    => [:selected :app]
  
  Add some orders on [page](http://localhost:3000/) and inspect the state of application:
  
    @state/orders
    => {:gig-06 3, :gig-05 1}