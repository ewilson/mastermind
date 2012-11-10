(ns wilsonericn.mm)

(def pegs #{:red :orange :yellow :green :blue :violet})

(defn choose-peg []
  (rand-nth (vec pegs)))

(defn choose-secret []
  (repeatedly 4 choose-peg))

(def secret (choose-secret))

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
  {:guess guess :clue (to-vector (evaluate guess secret))})

