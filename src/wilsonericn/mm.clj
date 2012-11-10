(ns wilsonericn.mm
  (:use wilsonericn.cli))

(def pegs #{:red :orange :yellow :green :blue :violet})

(defn choose-peg []
  (rand-nth (vec pegs)))

(defn choose-secret []
  (repeatedly 4 choose-peg))

(defn game []
  {:completed-rows [] :round 0 :secret (choose-secret)})

(def this-game (atom (game)))

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
    {:black exact :white (- unordered exact)}))

(defn to-vector [result]
  (let [blacks (:black result)
        whites (:white result)
        opens (- 4 (+ blacks whites))]
  (concat (repeat blacks :black) (repeat whites :white) (repeat opens :open))))

(defn submit [guess]
  {:guess guess :clue (to-vector (evaluate guess (:secret @this-game)))})

(defn reset-game []
  (reset! this-game (game)))

(defn play []
  (reset-game)
  (loop [round 0]
      (let [guess (read-input (read-line))
            row (submit guess)]
    (if (> 4 round)
        (do (println row))
      (recur (inc round))
      "FOO")))
  