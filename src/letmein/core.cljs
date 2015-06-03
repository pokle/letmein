(ns ^:figwheel-always letmein.core
    (:require
              [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state 
    (atom 
        {:text  "Hello world!"
         :email "foo@example.com"
         :pass  "asdf"}))

(defn hello-world []
  [:h1 (:text @app-state)])

(defn input [state-atom & key-path]
    [:input {:value (get-in @state-atom key-path)
             :on-change (fn [ev] (swap! state-atom assoc-in key-path (-> ev .-target .-value)))}])

(defn login-prompt [message]
    [:p 
      [:em message] [:br]
      [:label {:for } "email address:"] [input app-state :email] [:br]
      [input app-state :pass]
      ])

(reagent/render-component [login-prompt "Welcome"]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
) 

