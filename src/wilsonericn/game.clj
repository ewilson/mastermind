(ns wilsonericn.game
  (:use wilsonericn.mm
        wilsonericn.cli
        wilsonericn.solver))

(defn play-game [input-secret input-guess output]
  (let [secret (input-secret)]
    (loop [rounds []]
      (cond
        (= (:clue (last rounds)) (repeat 4 :black)) (output rounds)
        :else (let [guess (input-guess rounds)
                    round {:guess guess :clue (evaluate guess secret) :round count}]
                (recur (conj rounds round)))))))

(defn play [type]
  (cond 
    (= type :test) (play-game request-code request-comp-guess show-board)
    (= type :play) (play-game choose-secret request-guess show-board)
    :default "ERROR"))

(defn record-stats [rounds]
  (let [num (count rounds)
        code (:guess (last rounds))]
    (do (println "code: " code " Number of rounds: " num)
      num)))

(defn play-all []
  (let [secrets (map (fn [code] (fn [] code)) allcodes)]
    (doall (for [secret secrets] (play-game secret request-comp-guess record-stats)))))

(defn evaluate-solver []
  (println (sort (frequencies (play-all)))))    
