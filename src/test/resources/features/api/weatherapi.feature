@test
Feature: Verify Get Current Weather Data

Scenario Outline: Verify Current Weather API with Valid data
	Given The user have valid API key
	When I submit GET request with the "<lat>" and "<lon>"
	Then API should return status as 200
	And Then verify response body is JSON with tuples
		| temp     | regex ^-?\\d*\\.?\\d+$ |
		| wind_spd | regex ^-?\\d*\\.?\\d+$ |
		| precip   | regex ^-?\\d*\\.?\\d+$ |
		| snow     | regex ^-?\\d*\\.?\\d+$ |
#		| weather  | regex /\[\s*[^\[\]]*?\s*\]/g |
	Examples:
		| lat       |   lon        |
		| 40.730610 |   -73.935242 |
	    | 90        |   -92.222		|

#	Scenario Outline: Verify Current Weather API with invalid data
#		Given The user have valid API key
#		When I submit GET request with the "<lat>" and "<lon>"
#		Then verify  response code is <responseCode>
##		Then verify  response body is JSON with tuples <responseValidation
#
#
#		Examples:
#		Scenario			| lat       |   lon         | responseCode |
##		|blank lattitude	|  		    |   -73.935242  | 400        |
##		|blank Longitude	| 90 		|               | 400        |
#		|latitude with special character|$%|-73.935242|400|
#		|Logitude with special character|90|&&|400|

