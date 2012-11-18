(ns wilsonericn.game
  (:use wilsonericn.mm
        wilsonericn.cli
        wilsonericn.solver)
   (:gen-class :main true))

(defn play-game [input-secret input-guess output]
  (let [secret (input-secret)]
    (loop [count 1
           rounds []]
      (cond
        (= (:clue (last rounds)) (repeat 4 :black)) (output rounds)
        (> count 8) (losing-message rounds secret)
        :else (let [guess (input-guess rounds)
                    round {:guess guess :clue (evaluate guess secret) :round count}]
                (recur (inc count) (conj rounds round)))))))

(defn play [type]
  (cond 
    (= type :encode) (play-game request-code request-comp-guess show-board)
    (= type :decode) (play-game choose-secret request-guess show-board)
    :default "ERROR"))

(defn play-all [n]
  (let [secrets (take n (map (fn [code] (fn [] code)) allcodes))]
    (for [secret secrets] (play-game secret request-comp-guess identity))))
    
(defn go []
  (play :encode))

(defn -main [& args]
    (go))

