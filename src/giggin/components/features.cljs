(ns giggin.components.features
  (:require [reagent.core :as r]))

(defn canvas
  []
  (let [size (r/atom 10)
        id (js/setInterval
             (fn []
               (swap! size (fn [s]
                             (-> s
                                 inc
                                 (mod 200)))))
             1000)]
    (r/create-class
      {:display-name "canvas with square"
       :component-did-mount
         (fn [comp]
           (let [node (r/dom-node comp)
                 ctx (.getContext node "2d")]
             (.clearRect ctx 0 0 200 200)
             (.fillRect ctx 10 10 @size @size)))
       :reagent-render
         (fn []
           @size
           [:canvas {:style
                     {:width 200
                      :height 200
                      :border "1px solid green"}}])
       :component-did-update
         (fn [comp]
           (let [node (r/dom-node comp)
                 ctx (.getContext node "2d")]
             (.clearRect ctx 0 0 200 200)
             (.fillRect ctx 10 10 @size @size)))
       :component-will-unmount
         (fn [comp]
           (js/clearInterval id))})))

;; React
;; https://github.com/reagent-project/reagent/blob/master/doc/ReactFeatures.md






