(ns wilsonericn.solver
  (:use wilsonericn.mm))

(def pegvec (vec pegs))

(def allcodes (for [a pegvec b pegvec c pegvec d pegvec] [a b c d]))

(defn consistent [result]
  (fn [code] (= (:clue result) (evaluate code (:guess result)))))

(defn consistent-all [results]
  (reduce #(filter %2 %1) allcodes (map consistent results)))    