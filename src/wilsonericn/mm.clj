(ns wilsonericn.mm)

(def pegs #{:red :orange :yellow :green :blue :violet})

(defn secret-chooser [size]
  (fn [] (repeatedly size #(rand-nth (vec pegs)))))

;; Finds number of correct pegs in correct position
(defn exact-matches [guess actual]
  (count (filter true? (map = guess actual))))

;; Helper function - gives frequences with zeros
(defn peg-counts [coll]
  (merge (zipmap pegs (repeat 0)) (frequencies coll)))

;; Finds number of pegs correct allowing for rearrangement
(defn unordered-matches [guess actual]
    (apply + (vals (merge-with min (peg-counts guess) (peg-counts actual)))))

;; Evaluates a guess agains a code, returning a map with :black and :white counts
(defn evaluate [guess actual] 
  (let [exact (exact-matches guess actual)
        unordered (unordered-matches guess actual)]
    (concat (repeat exact :black) (repeat (- unordered exact) :white))))
