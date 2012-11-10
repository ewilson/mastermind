(ns wilsonericn.mm-test
  (:use clojure.test
        wilsonericn.mm))

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

(deftest converts-result-to-vector 
  (is (= [:open :open :open :open] (to-vector {:black 0 :white 0})))
  (is (= [:white :white :open :open] (to-vector {:black 0 :white 2})))
  (is (= [:black :black :white :open] (to-vector {:black 2 :white 1})))
  (is (= [:black :black :white :white] (to-vector {:black 2 :white 2}))))
