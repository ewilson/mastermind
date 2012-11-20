(ns wilsonericn.solver
  (:use wilsonericn.mm)
  (:require [clojure.math.combinatorics :as comb]))

;; Produces sequence of all possible codes for a given size
(defn allcodes [size]
  (apply comb/cartesian-product (repeat size pegs)))

;; Given a result (guess and clue) returns a function that determines if another code
;; would have produced the same clue
(defn consistent [result]
  (fn [code] (= (:clue result) (evaluate code (:guess result)))))

;; Uses all results to filter to the remaining possible codes
(defn consistent-all [results size]
  (reduce #(filter %2 %1) (allcodes size) (map consistent results))) 

(defn request-comp-guess [rounds size]
  (if (empty? rounds)
    (take size [:violet :violet :blue :blue :green :green :yellow :yellow :orange :orange :red :red])
    (first (consistent-all rounds size))))
