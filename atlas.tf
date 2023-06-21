terraform {
#  required_version = "0.15.1"
  backend "local" {
  }
  required_providers {
    mongodbatlas = {
      source  = "mongodb/mongodbatlas"
      version = "1.9.0"
    }
  }
}
provider "mongodbatlas" {
  public_key  = "fhtzvuxf"
  private_key = "732341c6-86b2-4ed3-9797-bf425cc3d53f"
}
resource "mongodbatlas_advanced_cluster" "atlascluster1" {
  project_id   = "646f1c5b72f24c07d42cf62a"
  name         = "userprofile"
  cluster_type = "REPLICASET"
  replication_specs {
    region_configs {
      electable_specs {
        instance_size = "M10"
        node_count    = 3
      }
      provider_name = "AWS"
      priority      = 7
      region_name   = "US_EAST_1"
    }
  }

}

resource "mongodbatlas_database_user" "testUser" {
  username           = "subash"
  password           = "subash"
  project_id         = "646f1c5b72f24c07d42cf62a"
  auth_database_name = "admin"

  roles {
    role_name     = "readAnyDatabase"
    database_name = "admin"
  }
  roles {
    role_name     = "readWrite"
    database_name = "admin"
  }

  roles {
    role_name     = "readWrite"
    database_name = "Users"
  }

  scopes {
    name   = "userprofile"
    type = "CLUSTER"
  }

}

resource "mongodbatlas_project_ip_access_list" "test" {
  project_id = "646f1c5b72f24c07d42cf62a"
  cidr_block = "103.10.31.102/32"
  comment    = "cidr block for tf acc testing"
}

output "standard_srv" {
  value = mongodbatlas_advanced_cluster.atlascluster1.connection_strings[0].standard_srv
}
