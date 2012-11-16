(ns wilsonericn.game
  (:use wilsonericn.mm
        wilsonericn.cli
        wilsonericn.solver))

(defn play []
  (let [secret (choose-secret)]
    (loop [count 1
           rounds []]
      (show-board rounds)
      (cond
        (= (:clue (last rounds)) (repeat 4 :black)) "You WIN!"
        (> count 8) (losing-message secret)
        :else (let [guess (request-code)
                    round {:guess guess :clue (evaluate guess secret) :round count}]
                (recur (inc count) (conj rounds round)))))))

(defn playc []
  (let [secret (request-code)]
    (loop [count 1
           rounds []]
      (cond
        (= (:clue (last rounds)) (repeat 4 :black)) (do
                                                      (show-board rounds)
                                                      (println "I WIN!"))
        (> count 8) (losing-message secret)
        :else (let [guess (request-comp-guess rounds)
                    round {:guess guess :clue (evaluate guess secret) :round count}]
                (recur (inc count) (conj rounds round)))))))
