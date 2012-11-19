(ns wilsonericn.runner
  (:require  [clojure.tools.cli :as cli])
  (:use wilsonericn.game)
  (:gen-class :main true))

(defn -main [& args]
  (let [[opts args banner]
        (cli/cli args
          ["-h" "--help" "Show help" :flag true :default false]
          ["-t" "--test" "Test Mastermind solver" :flag true] 
          ["-p" "--play" "Play Mastermind" :flag true]
          ["-v" "--verbose" "Give per-game results" :flag true] 
          ["-s" "--size" "Size of code strings" :default 4])]
    (when (:help opts)
      (println banner)
      (System/exit 0))
    (cond
      (:test opts) (play :test)
      (:play opts) (play :play)
      :default (evaluate-solver))))
