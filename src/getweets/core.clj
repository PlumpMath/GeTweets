
(ns getweets.core
  (:use cheshire.core
        clojure.java.io))

(def ^{:doc "URL for the Twitter user timeline endpoint"}
       twitter "https://api.twitter.com/1/statuses/user_timeline.json?screen_name=%s&page=%d&count=%d&trim_user=1&include_rts=1")

(def ^{:doc "The number of tweets to fetch in each page of results"}
       per-page 200)

;; Public

(defn fetch
  "Fetch a users tweets by screen_name.  Just use the single arity version of the function, the multiple
   artity version is used recursively to fetch pages of tweets."
  ([screen_name] (fetch screen_name 1 []))
  ([screen_name page all-tweets]
    (let [url (format twitter screen_name page per-page)
          new-tweets (parse-stream (reader url) true)
          tweets (concat all-tweets new-tweets)]
      (if (< (count new-tweets) per-page)
          tweets
          (recur screen_name (inc page) tweets)))))

;; Main

(defn -main [& [screen_name]]
  (prn (fetch screen_name)))

