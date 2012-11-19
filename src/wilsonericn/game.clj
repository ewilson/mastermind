(ns wilsonericn.game
  (:use wilsonericn.mm
        wilsonericn.cli
        wilsonericn.solver))

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

(defn record-stats [rounds]
  (let [num (count rounds)
        code (:guess (last rounds))]
    (do (println "code: " code " Number of rounds: " num)
      num)))

(defn play-all []
  (let [secrets (map (fn [code] (fn [] code)) allcodes)]
    (doall (for [secret secrets] (play-game secret request-comp-guess record-stats)))))

;; Add histogram for more readable output
(defn evaluate-solver []
  (let [results (frequencies (play-all))]
    prn results))
    
   