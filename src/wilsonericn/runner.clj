(ns wilsonericn.runner
  (:require  [clojure.tools.cli :as cli])
  (:use wilsonericn.game)
  (:gen-class :main true))

(defn -main [& args]
  (let [[opts args usage]
        (cli/cli args
          ["-h" "--help" "Show help" :flag true :default false]
          ["-l" "--limit" "Number of attempts allowed" :default 12] ;; not implemented
          ["-s" "--size" "Size of code strings" :default 4] ;; not implemented
          ["-e" "--encode" "Set the encoded string" :flag true :default true])]
    (when (:help opts)
      (println usage)
      (System/exit 0))
    (if (:encode opts)
      (play :encode)
      (play :decode))))
