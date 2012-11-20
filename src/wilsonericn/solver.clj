(ns wilsonericn.solver
  (:use wilsonericn.mm)
  (:require [clojure.math.combinatorics :as comb]))

(defn allcodes [size]
  (apply comb/cartesian-product (repeat size pegs)))

(defn consistent [result]
  (fn [code] (= (:clue result) (evaluate code (:guess result)))))

(defn consistent-all [results size]
  (reduce #(filter %2 %1) (allcodes size) (map consistent results))) 

(defn request-comp-guess [rounds size]
  (if (empty? rounds)
    (take size [:violet :violet :blue :blue :green :green :yellow :yellow :orange :orange :red :red])
    (first (consistent-all rounds size))))
