local typedefs = require "kong.db.schema.typedefs"

return {
  name = "custom-auth",
  fields = {
    { consumer = typedefs.no_consumer },
    { protocols = typedefs.protocols_http },
    { config = {
        type = "record",
        fields = {
          { auth_service_url = { type = "string", required = trueS } },
        },
      },
    },
  },
}