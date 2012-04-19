
(ns getweets.core
  (:use cheshire.core
        clojure.java.io))

(def ^{:doc "URL for the Twitter user timeline endpoint"}
       twitter "https://api.twitter.com/1/statuses/user_timeline.json?screen_name=%s&page=%d&count=%d&trim_user=1&include_rts=1")

(def ^{:doc "The number of tweets to fetch in each page of results"}
       per-page 25)

(defn- fetch
  "Fetch a page of tweets"
  ([screen_name] (fetch screen_name 1))
  ([screen_name page]
    (let [url (format twitter screen_name page per-page)
          tweets (parse-stream (reader url) true)]
      (if (< (count tweets) per-page)
          (recur screen_name (inc page))))))

;; Main

(defn -main [& [screen_name]]
  (fetch screen_name))

