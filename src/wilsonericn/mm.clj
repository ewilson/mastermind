(ns wilsonericn.mm
  (:use [clojure.test]))

(def pegs #{:red :orange :yellow :green :blue :violet})

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

(run-all-tests)

(deftest finds-exact-matches 
  (is (= 0 (exact-matches [:red :red :blue :green] [:yellow :blue :green :red])))
  (is (= 1 (exact-matches [:red :red :blue :green] [:red :blue :green :red])))
  (is (= 2 (exact-matches [:red :red :blue :green] [:red :blue :green :green])))
  (is (= 3 (exact-matches [:red :red :blue :green] [:red :red :green :green])))
  (is (= 4 (exact-matches [:red :red :blue :green] [:red :red :blue :green]))))

(deftest finds-unordered-matches 
  (is (= 0 (unordered-matches [:red :red :blue :green] [:yellow :violet :orange :orange])))
  (is (= 1 (unordered-matches [:red :red :blue :green] [:yellow :blue :violet :yellow])))
  (is (= 2 (unordered-matches [:red :red :blue :green] [:red :yellow :red :yellow])))
  (is (= 3 (unordered-matches [:red :red :blue :green] [:red :blue :green :green])))
  (is (= 4 (unordered-matches [:red :red :blue :green] [:green :red :red :blue]))))

(deftest evaluates-guesses 
  (is (= {:black 0 :white 0} (evaluate [:red :red :blue :green] [:yellow :violet :orange :orange])))
  (is (= {:black 0 :white 1} (evaluate [:red :red :blue :green] [:yellow :blue :violet :yellow])))
  (is (= {:black 1 :white 1} (evaluate [:red :red :blue :green] [:red :yellow :red :yellow])))
  (is (= {:black 2 :white 1} (evaluate [:red :red :blue :green] [:red :blue :green :green])))
  (is (= {:black 1 :white 3} (evaluate [:red :red :blue :green] [:green :red :red :blue])))
  (is (= {:black 0 :white 3} (evaluate [:red :red :blue :green] [:yellow :blue :green :red])))
  (is (= {:black 1 :white 3} (evaluate [:red :red :blue :green] [:red :blue :green :red])))
  (is (= {:black 2 :white 1} (evaluate [:red :red :blue :green] [:red :blue :green :green])))
  (is (= {:black 3 :white 0} (evaluate [:red :red :blue :green] [:red :red :green :green])))
  (is (= {:black 4 :white 0} (evaluate [:red :red :blue :green] [:red :red :blue :green]))))
