(ns wilsonericn.game
  (:use wilsonericn.mm
        wilsonericn.cli
        wilsonericn.solver))

(defn play-game [input-secret input-guess]
  (let [secret (input-secret)]
    (loop [count 1
           rounds []]
      (cond
        (= (:clue (last rounds)) (repeat 4 :black)) (show-board rounds)
        (> count 8) (losing-message rounds secret)
        :else (let [guess (input-guess rounds)
                    round {:guess guess :clue (evaluate guess secret) :round count}]
                (recur (inc count) (conj rounds round)))))))

(defn play [type]
  (cond 
    (= type :encode) (play-game request-code request-comp-guess)
    (= type :decode) (play-game choose-secret request-guess)
    :default "ERROR"))