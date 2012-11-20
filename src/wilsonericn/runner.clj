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
          ["-s" "--size" "Size of code strings (2 - 12)" :default 4 :parse-fn #(Integer. %)])]
    (when (:help opts)
      (println banner)
      (System/exit 0))
    (let [s (:size opts)]
      (cond
        (or (> s 12) (< s 2)) (do (println "Size" s "not supported")
                                (System/exit 0))
        (:test opts) (play :test s)
        (:play opts) (play :play s)
        :default (evaluate-solver s)))))
