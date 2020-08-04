package ru.digital.league.x5.sign.bindings.config.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomAccessTokenConverter extends DefaultAccessTokenConverter  {

	private boolean includeGrantType = true;

	private final String ROLES = "roles";

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
		Map<String, String> parameters = new HashMap<>();
		Set<String> scope = extractScope(map);
		Authentication user = new DefaultUserAuthenticationConverter().extractAuthentication(map);
		String clientId = (String) map.get(CLIENT_ID);
		parameters.put(CLIENT_ID, clientId);
		if (includeGrantType && map.containsKey(GRANT_TYPE)) {
			parameters.put(GRANT_TYPE, (String) map.get(GRANT_TYPE));
		}
		Set<String> resourceIds = new LinkedHashSet<>(map.containsKey(AUD) ? getAudience(map) : Collections.emptySet());

		Collection<? extends GrantedAuthority> authorities = null;
		if (user == null) {
			if (map.containsKey(AUTHORITIES)) {
				@SuppressWarnings("unchecked")
				String[] roles = ((Collection<String>) map.get(AUTHORITIES)).toArray(new String[0]);
				authorities = AuthorityUtils.createAuthorityList(roles);
			}

			if (authorities == null && map.containsKey(ROLES)) {
				@SuppressWarnings("unchecked")
				String[] roles = ((Collection<String>) map.get(ROLES)).toArray(new String[0]);
				authorities = AuthorityUtils.createAuthorityList(roles);
			}

			if (authorities == null) {
				List<String> roles = findRolesAttributeInMap(map);
				if (!roles.isEmpty()) {
					authorities = AuthorityUtils.createAuthorityList(roles.toArray(new String[0]));
				}
			}
		}

		OAuth2Request request = new OAuth2Request(parameters, clientId, authorities, true, scope, resourceIds, null, null,
				null);
		return new CustomOAuth2Authentication(request, user, map);
	}

	private Set<String> extractScope(Map<String, ?> map) {
		Set<String> scope = Collections.emptySet();
		if (map.containsKey(SCOPE)) {
			Object scopeObj = map.get(SCOPE);
			if (String.class.isInstance(scopeObj)) {
				scope = new LinkedHashSet<>(Arrays.asList(String.class.cast(scopeObj).split(" ")));
			} else if (Collection.class.isAssignableFrom(scopeObj.getClass())) {
				@SuppressWarnings("unchecked")
				Collection<String> scopeColl = (Collection<String>) scopeObj;
				scope = new LinkedHashSet<>(scopeColl);	// Preserve ordering
			}
		}
		return scope;
	}

	private Collection<String> getAudience(Map<String, ?> map) {
		Object auds = map.get(AUD);
		if (auds instanceof Collection) {
			@SuppressWarnings("unchecked")
			Collection<String> result = (Collection<String>) auds;
			return result;
		}
		return Collections.singleton((String)auds);
	}

	@SuppressWarnings("unchecked")
	private List<String> findRolesAttributeInMap(Map<String, ?> map) {
		List<String> roles = new ArrayList<>();
		for (Map.Entry<String, ?> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (ROLES.equals(key) && value instanceof Collection) {
				roles.addAll((Collection<? extends String>) value);
			} else if (value instanceof Map) {
				roles.addAll(findRolesAttributeInMap((Map<String, ?>) value));
			}
		}
		return roles;
	}
}
