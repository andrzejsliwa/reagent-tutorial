(ns giggin.core
  (:require [reagent.core :as r]
            [giggin.components.header :refer [header]]
            [giggin.components.gigs :refer [gigs]]
            [giggin.components.orders :refer [orders]]
            [giggin.components.features :refer [canvas]]
            [giggin.components.footer :refer [footer]]))




(defn app
  []
  [:div.container
   [header]
   [gigs]
   [orders]
   [footer]])

(+ 1 2)

(defn ^:export main
  []
  (r/render
    [app]
    (.getElementById js/document "app")))

