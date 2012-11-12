(ns wilsonericn.cli
  (:use [clojure.set]))

(def conversion {\R :red \O :orange \Y :yellow \G :green \B :blue \V :violet})

(def reverse-guess (map-invert conversion))

(def reverse-clue {:black \X :white \O})

(defn convert-input [input]
  (map conversion (.toUpperCase (clojure.string/replace input #"\W" ""))))

(defn request-guess []
  (println "Enter guess")
  (convert-input (read-line)))
  
(defn convert-output [round]
  (let [guess (apply str (map reverse-conv (:guess round)))
        clue (apply str (map reverse-clue (:clue round)))
        n (:round round)]
    (str "--------------\n(" n ")  " guess "  " clue "\n")))

(defn sent-all [rounds]
  (