@post
Feature: Verify GET weather by postcode API
Scenario Outline: Verify Current by post code
	Given The user have valid API key
#	When The user sents GET request and get the state code value
	When I submit GET request with the "<postcode>" ,"<country>" and "<include>"
	Then Postcode API should return status as 200
	And Then verify postcode API response body is JSON with tuples
		| temp     | regex ^-?\\d*\\.?\\d+$ |
		| wind_spd | regex ^-?\\d*\\.?\\d+$ |
		| precip   | regex ^-?\\d*\\.?\\d+$ |
		| snow     | regex ^-?\\d*\\.?\\d+$ |

	Examples:

		|postcode|country|include|
	|   2217     | Australia      |  minutely     |
	|  2210      |  Australia     | minutely      |

