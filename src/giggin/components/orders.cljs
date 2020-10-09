(ns giggin.components.orders
  (:require [giggin.state :as state]
            [giggin.helpers :refer [format-price]]
            [giggin.components.checkout-modal :refer [checkout-modal]]))

(defn calculate-total
  []
  (->> @state/orders
    (map (fn [[id quantity]] (* quantity (get-in @state/gigs [id :price]))) @state/orders)
    (reduce +)))

(defn reset-orders
  []
  #(reset! state/orders {}))

(defn remove-order
  [id]
  #(swap! state/orders dissoc id))

(defn total
  []
  [:div.total
   [:hr]
   [:div.item
    [:div.content "Total"]
    [:div.action
     [:div.price (format-price (calculate-total))]]
    [:button.btn.btn--link.tooltip
     {:data-tooltip "Remove all"
      :on-click (reset-orders)}
     [:i.icon.icon--delete]]]
   [checkout-modal]])

(defn order-line
  [{img :img title :title price :price}
   quantity id]
  [:div.item {:key id}
   [:div.img
    [:img {:src img
           :alt title}]]
   [:div.content
    [:p.title (str title " \u00D7" quantity)]]
   [:div.action
    [:div.price (format-price (* price quantity))]
    [:button.btn.btn--link.tooltip
     {:data-tooltip "Remove"
      :on-click (remove-order id)}
     [:i.icon.icon--cross]]]])

(defn all-orders
  []
  [:div.order
   [:div.body
    (for [[id quantity] @state/orders]
      (let [gig (get @state/gigs id)]
        (order-line gig quantity id)))]
   (total)])

(defn no-orders
  []
  [:div.empty
   [:div.title "You don't have any orders"]
   [:div.subtitle "Click on a + to add an order"]])

(defn orders
  []
  [:aside
   (if (empty? @state/orders)
     (no-orders)
     (all-orders))])
