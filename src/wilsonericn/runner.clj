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
          ["-s" "--size" "Size of code strings" :default 4 :parse-fn #(Integer. %)])]
    (when (:help opts)
      (println banner)
      (System/exit 0))
    (let [s (:size opts)]
      (cond
        (:test opts) (play :test s)
        (:play opts) (play :play s)
        :default (evaluate-solver s)))))
