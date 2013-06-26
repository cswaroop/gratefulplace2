(ns gratefulplace.controllers.watches
  (:require [gratefulplace.db.validations :as validations]
            [gratefulplace.db.query :as db]
            [gratefulplace.db.maprules :as mr]
            [flyingmachine.cartographer.core :as c]
            [cemerick.friend :as friend])
  (:use [liberator.core :only [defresource]]
        gratefulplace.controllers.shared
        gratefulplace.models.permissions
        gratefulplace.utils))

(defresource create! [params auth]
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :authorized? (logged-in? auth)

  :post! (fn [_]
           (println "PARAMS" params)
           (db/t [{:db/id #db/id[:db.part/user]
                   :watch/kind :watch
                   :watch/topic (:topic-id params)
                   :watch/user (:user-id params)}])
           {:record {}})
  :handle-created record-in-ctx)