(ns wilsonericn.mm
  (:use wilsonericn.cli))

(def pegs #{:red :orange :yellow :green :blue :violet})

(defn choose-secret []
  (repeatedly 4 #(rand-nth (vec pegs))))

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
      (show-board rounds)
      (cond
        (= (:clue (last rounds)) (repeat 4 :black)) "You WIN!"
        (> count 8) (losing-message secret)
        :else (let [guess (request-guess)
                    round {:guess guess :clue (evaluate guess secret) :round count}]
                (recur (inc count) (conj rounds round)))))))
