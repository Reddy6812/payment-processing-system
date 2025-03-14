variable "aws_region" {
  type    = string
  default = "us-east-1"
}

variable "aws_az" {
  type    = string
  default = "us-east-1a"
}

variable "ami_id" {
  type    = string
  default = "ami-12345678"  # Replace with a valid AMI ID for your region
}

variable "instance_type" {
  type    = string
  default = "t2.micro"
}
