(ns wilsonericn.cli
  (:require [clojure.set :refer [map-invert]]))

(def conversion {\R :red \O :orange \Y :yellow \G :green \B :blue \V :violet})

(def reverse-code (map-invert conversion))

(def reverse-clue {:black \X :white \O})

(defn convert-input [input]
  (map conversion (.toUpperCase (clojure.string/replace input #"\W" ""))))

(defn request-guess []
  (println "Enter guess")
  (convert-input (read-line)))

(defn losing-message [secret]
  (println "You are out of guesses.")
  (printf "The secret code was: %s" (apply str (map reverse-code secret))))

(defn convert-output [round]
  (let [guess (apply str (map reverse-code (:guess round)))
        clue (apply str (map reverse-clue (:clue round)))
        n (:round round)]
    (format "-------------------%n(%d)  |%s|  |%-4s|" n guess clue)))

(defn show-board [rounds]
  (doseq [r rounds]
    (println (convert-output r))))