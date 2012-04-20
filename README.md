
# GeTweets

A simple Clojure tool for easily fetching a users tweets.

## Usage

The program is run through Leiningen, and you just specify the screen name
of the user you want to fetch tweets for.

```bash
lein run your_name
```

All of that users tweets will then be output to the console as JSON.

## Using as a library

Alternatively you can use GeTweets as a library, it's available via Clojars.

```clojure
(:require [getweets.core :as getweets])

(let [tweets (getweets/fetch "my_name")]
  ...)
```

## License

Distributed under the Eclipse Public License, the same as Clojure.

