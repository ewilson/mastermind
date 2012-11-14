(ns wilsonericn.game
  (:use wilsonericn.mm
        wilsonericn.cli))

(defn play []
  (let [secret (choose-secret)]
    (loop [count 1
           rounds []]
      (show-board rounds)
      (cond
        (= (:clue (last rounds)) (repeat 4 :black)) "You WIN!"
        (> count 8) (losing-message secret)
        :else (let [guess (request-guess)
                    round {:guess guess :clue (evaluate guess secret) :round count}]
                (recur (inc count) (conj rounds round)))))))
