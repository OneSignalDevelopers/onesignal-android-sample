#!/bin/sh

curl -X POST "https://onesignal.com/api/v1/notifications" \
   -H "Content-Type: application/json; charset=utf-8" \
   -H "Authorization: Basic $OS_REST_API_KEY" \
   -d '{
       "app_id": "<APP_ID>",
       "collapse_id": "13",
       "include_aliases": {
            "external_id": ["will_test"]
        },
        "target_channel": "push",
       "contents": {"en": "You shouldnt see this"},
       "headings": {"en": "Dismiss notifications"},
       "data": {
            "live_notification": {
                "key": "another",
                "event": "dismiss",
                "immutable_attributes": {},
                "initial_attributes": {},
                "event_updates": {}
            }
        }
   }'
