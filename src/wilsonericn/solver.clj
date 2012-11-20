(ns wilsonericn.solver
  (:use wilsonericn.mm)
  (:require [clojure.math.combinatorics :as comb]))

(def pegvec (vec pegs))

(defn allcodes [size]
  (apply comb/cartesian-product (repeat size pegvec)))

(defn consistent [result]
  (fn [code] (= (:clue result) (evaluate code (:guess result)))))

(defn consistent-all [results size]
  (reduce #(filter %2 %1) (allcodes size) (map consistent results))) 

(defn request-comp-guess [rounds size]
  (if (empty? rounds)
    (take size [:red :red :orange :orange :yellow :yellow :green :green :blue :blue :violet :violet])
    (first (consistent-all rounds size))))
