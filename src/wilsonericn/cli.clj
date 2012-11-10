(ns wilsonericn.cli)

(def conversion {\R :red \O :orange \Y :yellow \G :green \B :blue \V :violet})

(defn read-input [input]
  (map conversion (.toUpperCase (clojure.string/replace input #"\W" ""))))
  
