(ns wilsonericn.mm
  (:use wilsonericn.cli))

(def pegs #{:red :orange :yellow :green :blue :violet})

(defn choose-peg []
  (rand-nth (vec pegs)))

(defn choose-secret []
  (repeatedly 4 choose-peg))

(defn exact-matches [guess actual]
  (count (filter true? (map = guess actual))))

(defn unordered-matches [guess actual]
  (let [guess-cts (frequencies guess)
        actual-cts (frequencies actual)
        common-keys (filter (set (keys guess-cts)) (keys actual-cts))]
    (apply + (map (fn [k] (Math/min (k guess-cts) (k actual-cts))) common-keys))))

(defn evaluate [guess actual] 
  (let [exact (exact-matches guess actual)
        unordered (unordered-matches guess actual)]
    (concat (repeat exact :black) (repeat (- unordered exact) :white))))

(defn play []
  (let [secret (choose-secret)]
    (loop [count 1
           rounds []]
      (if (< 8 count)
        (do 
          (println secret)
          "You lose")
        (do
          (println rounds)
          (println "Input guess")
          (let [guess (read-input (read-line))
                round {:guess guess :clue (evaluate guess secret) :round count}]
            (if (= (:clue round) [:black :black :black :black])
              (do 
                (println round)
                (println "You WIN!"))
              (do 
                (recur (inc count) (conj rounds round))))))))))