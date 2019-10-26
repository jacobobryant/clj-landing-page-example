(ns web.core
  (:require [trident.staticweb :as tsweb]))

(def primary "#008577")
(def primary-text "white")

(def a-defaults {:href "#" :style {:color primary-text}})

(defn a [contents]
  [:a a-defaults contents])

(defn gap [size]
  [:div {:style {:width size :height size}}])

(def coming-soon
  [:div {:style {:padding "20px"
                 :border "2px solid"
                 :border-color primary
                 :border-radius "10px"}}
   [:p {:style {:font-size "28px"
                :text-align "center"}}
    "Coming soon"]
   [:ul
    [:li "Offline playback for Spotify"]
    [:li "iPhone app"]
    [:li "Features for supporting independent artists"]]
   [:p.before-signup {:style {:margin-left "13px"}} "Sign up for new feature announcements:"]
   [:p.after-signup {:style {:text-align "center"
                             :display "none"}} "Thanks for signing up!"]
   [:div.before-signup {:style {:width "100%"
                                :text-align "center"
                                :display "flex"}}
    [:input#email.form-control {:type "email"
                                :placeholder "Enter email"
                                :aria-describedby "emailHelp"
                                :style {:border-top-right-radius 0
                                        :border-bottom-right-radius 0}}]
    [:button.btn.btn-primary
     {:style {:width "200px"
              :background-color primary
              :border-color primary
              :border-top-left-radius 0
              :border-bottom-left-radius 0}
      :onclick "signup()"}
     "Sign&nbsp;up"]]])

(def bootstrap-4
  [:link {:rel "stylesheet"
          :href "https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          :integrity "sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          :crossorigin "anonymous"}])

(def head
  [:head
   [:title "Lagukan"]
   bootstrap-4
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   [:meta {:charset "utf-8"}]
   [:script {:src "index.js"}]])

(def navbar
  [:nav.navbar.static-top
   {:style {:background-color primary
            :color primary-text}}
   [:div.container
    [:div.navbar-header
     [:a.navbar-brand (assoc-in a-defaults [:style :font-size] "32px")
      [:img {:src "logo.png"
             :style {:height "32px"}}]
      " Lagukan"]]]])

(def home-contents
  [:div.container
   (gap "30px")
   [:div.row
    [:div.col-lg-7.d-flex.flex-column.justify-content-center.align-items-center
     [:div {:style {:max-width "520px"}}
      [:div {:style {:font-size "36px"}}
       "Finally, a smarter music&nbsp;player."]
      [:p {:style {:font-size "20px"}}
       "Just skip songs that don't fit your current mood,
       and our algorithms will figure out what to play&mdash;no more
       listening to the same stuff over and over. Plays from Spotify
       or your MP3 collection."]
      (gap "10px")
      [:div.d-flex {:style {:justify-content "space-around"}}
       (a [:img {:style {:opacity 1
                         :height 69
                         :margin "-12px"}
                 :alt "Get it on Google Play"
                 :src "https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png"}])
       (a [:button.btn.btn-primary
           {:style {:background-color primary
                    :border-color primary
                    :min-height "45px"}}
           "Install for Desktop"])]
      (gap "50px")
      [:div.d-none.d-lg-block
       (gap "30px")
       coming-soon]]]
    [:div.col-lg-5.justify-content-center.align-items-center.d-flex
     [:img {:src "screenshot.png"
            :style {:max-width "400px"}}]]]
   (gap "40px")
   [:div.row.d-flex.d-lg-none.justify-content-center.align-items-center
    [:div.col.d-flex
     {:style {:max-width "500px"}}
     coming-soon]]])

(def footer
  [:footer.page-footer.font-small.blue.pt-4
   {:style {:background-color primary
            :color primary-text}}
   [:div.container
    [:div.row
     [:div.col-lg-7
      [:div.d-flex
       [:div
        [:div (a "Contact us")]
        [:div (a "Twitter")]
        [:div (a "Terms of Service")]
        [:div (a "Privacy Policy")]]
       [:div.flex-grow-1]
       [:div
        [:p "Lagukan" [:br]
         "1600 Pennsylvania Avenue NW" [:br]
         "Washington, DC 20500"]]]
      [:div
       [:small "Google Play and the Google Play logo are trademarks of Google LLC."]]]]]
   (gap "30px")])

(defn page [contents]
  (tsweb/html
    [:html {:lang "en"
            :style {:height "100%"}}
     head
     [:body.d-flex.flex-column {:style {:min-height "100%"}}
      navbar
      contents
      [:div.flex-grow-1]
      (gap "50px")
      footer]]))

(def home-page (page home-contents))

(defn -main []
  (spit "public/index.html" home-page))
