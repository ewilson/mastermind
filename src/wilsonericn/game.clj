(ns wilsonericn.game
  (:use wilsonericn.mm
        wilsonericn.cli
        wilsonericn.solver))

(defn play-game [input-secret input-guess output]
  (let [secret (input-secret)
        size (count secret)]
    (loop [rounds []]
      (cond
        (= (:clue (last rounds)) (repeat size :black)) (output rounds)
        :else (let [guess (input-guess rounds size)
                    round {:guess guess :clue (evaluate guess secret) :round count}]
                (recur (conj rounds round)))))))

(defn play [type size]
  (cond 
    (= type :test) (play-game request-code request-comp-guess show-board)
    (= type :play) (play-game (secret-chooser size) request-guess show-board)
    :default "ERROR"))

(defn record-stats [rounds]
  (let [num (count rounds)
        code (:guess (last rounds))]
    (do (println "code: " code " Number of rounds: " num)
      num)))

(defn play-all [size]
  (let [secrets (map (fn [code] (fn [] code)) (allcodes size))]
    (doall (for [secret secrets] (play-game secret request-comp-guess record-stats)))))

(defn evaluate-solver [size]
  (println (time (sort (frequencies (play-all size))))))  
