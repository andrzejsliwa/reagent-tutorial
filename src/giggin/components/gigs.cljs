(ns giggin.components.gigs
  (:require [giggin.state :as state]
            [giggin.helpers :refer [format-price]]
            [reagent.core :as r]))


(defn add-order
  [id]
  #(swap! state/orders update id inc))

(defn render-gig
  [{:keys [id img title price desc]}]

  [:div.gig {:key id}
   [:img.gig__artwork {:src img :alt title}]
   [:div.gig__body
    [:div.gig__title
     [:div.btn.btn--primary.float--right.tooltip
      {:data-tooltip "Add to order"
       :on-click (add-order id)}
      [:i.icon.icon--plus]] title]
    [:p.gig__price (format-price price)]
    [:p.gig__decs desc]]])

(defn gigs
  []
  (let [modal (r/atom false)]
    (fn []
      [:main
       [:div.gigs
        [:button.add-gig
         {:on-click #(reset! modal true)}
         [:div.add__title
          [:i.icon.icon--plus]
          [:p "Add gig"]]]
        (for [gig (vals @state/gigs)]
          (render-gig gig))]])))