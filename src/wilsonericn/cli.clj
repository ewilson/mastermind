(ns wilsonericn.cli
  (:require [clojure.string :as str])
  (:require [clojure.set :refer [map-invert]]))

(def conversion {\R :red \O :orange \Y :yellow \G :green \B :blue \V :violet})

(def reverse-code (map-invert conversion))

(def reverse-clue {:black \X :white \O})

(defn convert-input [input]
  (map conversion (str/upper-case (str/replace input #"\W" ""))))

(defn convert-output [round]
  (let [guess (apply str (map reverse-code (:guess round)))
        clue (apply str (map reverse-clue (:clue round)))]
    (format "--------------%n|%s|  |%-4s|" guess clue)))

(defn show-board [rounds]
  (doseq [r rounds]
    (println (convert-output r)))
  rounds)

(defn request-code []
  (println "Enter code")
  (convert-input (read-line)))

(defn request-guess [rounds]
  (show-board rounds)
  (request-code))


